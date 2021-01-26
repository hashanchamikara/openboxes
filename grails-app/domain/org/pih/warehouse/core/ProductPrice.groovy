/**
* Copyright (c) 2012 Partners In Health.  All rights reserved.
* The use and distribution terms for this software are covered by the
* Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
* which can be found in the file epl-v10.html at the root of this distribution.
* By using this software in any fashion, you are agreeing to be bound by
* the terms of this license.
* You must not remove this notice, or any other, from this software.
**/
package org.pih.warehouse.core

import org.pih.warehouse.auth.AuthService
import org.pih.warehouse.product.ProductPackage

class ProductPrice implements Serializable {

    def beforeInsert = {
        def currentUser = AuthService.currentUser.get()
        if (currentUser) {
            createdBy = currentUser
            updatedBy = currentUser
        }
    }
    def beforeUpdate = {
        def currentUser = AuthService.currentUser.get()
        if (currentUser) {
            updatedBy = currentUser
        }
    }

    String id

    PriceTypeCode type = PriceTypeCode.DEFAULT_PRICE
    BigDecimal price
    UnitOfMeasure currency

    Date startDate
    Date endDate

    // Audit fields
    Date dateCreated
    Date lastUpdated
    User createdBy
    User updatedBy

    static belongsTo = [ProductPackage]

    static mapping = {
        id generator: 'uuid'
    }

    static constraints = {
        type(nullable: false)
        price(nullable: false)
        currency(nullable: true)
        startDate(nullable: true)
        endDate(nullable: true)
        updatedBy(nullable: true)
        createdBy(nullable: true)
    }

    String toString() {
        return id
    }
}
