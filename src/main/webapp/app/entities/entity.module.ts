import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'company',
        loadChildren: './company/company.module#DoraposCompanyModule'
      },
      {
        path: 'shop',
        loadChildren: './shop/shop.module#DoraposShopModule'
      },
      {
        path: 'shop-section',
        loadChildren: './shop-section/shop-section.module#DoraposShopSectionModule'
      },
      {
        path: 'section-table',
        loadChildren: './section-table/section-table.module#DoraposSectionTableModule'
      },
      {
        path: 'system-events-history',
        loadChildren: './system-events-history/system-events-history.module#DoraposSystemEventsHistoryModule'
      },
      {
        path: 'product',
        loadChildren: './product/product.module#DoraposProductModule'
      },
      {
        path: 'product-category',
        loadChildren: './product-category/product-category.module#DoraposProductCategoryModule'
      },
      {
        path: 'product-variant',
        loadChildren: './product-variant/product-variant.module#DoraposProductVariantModule'
      },
      {
        path: 'product-extra',
        loadChildren: './product-extra/product-extra.module#DoraposProductExtraModule'
      },
      {
        path: 'product-type',
        loadChildren: './product-type/product-type.module#DoraposProductTypeModule'
      },
      {
        path: 'system-config',
        loadChildren: './system-config/system-config.module#DoraposSystemConfigModule'
      },
      {
        path: 'email-balancer',
        loadChildren: './email-balancer/email-balancer.module#DoraposEmailBalancerModule'
      },
      {
        path: 'profile',
        loadChildren: './profile/profile.module#DoraposProfileModule'
      },
      {
        path: 'employee-timesheet',
        loadChildren: './employee-timesheet/employee-timesheet.module#DoraposEmployeeTimesheetModule'
      },
      {
        path: 'orders',
        loadChildren: './orders/orders.module#DoraposOrdersModule'
      },
      {
        path: 'orders-line',
        loadChildren: './orders-line/orders-line.module#DoraposOrdersLineModule'
      },
      {
        path: 'orders-line-variant',
        loadChildren: './orders-line-variant/orders-line-variant.module#DoraposOrdersLineVariantModule'
      },
      {
        path: 'orders-line-extra',
        loadChildren: './orders-line-extra/orders-line-extra.module#DoraposOrdersLineExtraModule'
      },
      {
        path: 'discount',
        loadChildren: './discount/discount.module#DoraposDiscountModule'
      },
      {
        path: 'tax',
        loadChildren: './tax/tax.module#DoraposTaxModule'
      },
      {
        path: 'payment-method',
        loadChildren: './payment-method/payment-method.module#DoraposPaymentMethodModule'
      },
      {
        path: 'suspension-history',
        loadChildren: './suspension-history/suspension-history.module#DoraposSuspensionHistoryModule'
      },
      {
        path: 'shop-device',
        loadChildren: './shop-device/shop-device.module#DoraposShopDeviceModule'
      },
      {
        path: 'payment-method-config',
        loadChildren: './payment-method-config/payment-method-config.module#DoraposPaymentMethodConfigModule'
      },
      {
        path: 'payment',
        loadChildren: './payment/payment.module#DoraposPaymentModule'
      },
      {
        path: 'shop-change',
        loadChildren: './shop-change/shop-change.module#DoraposShopChangeModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraposEntityModule {}
