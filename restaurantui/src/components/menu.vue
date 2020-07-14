<template>
  <v-content>
    <v-data-table
      :headers="table.headers"
      :items="table.desserts"
      :items-per-page="table.perPage"
      class="elevation-1"
      :loading="table.loading"
      hide-default-footer
    >
      <template v-slot:item.type="{ item }">{{menuType[item.type]}}</template>
      <template v-slot:item.sum="{ item }">{{Number(item.num) * Number(item.price)}}</template>
      <template v-slot:item.actions="{ item }">
        <v-btn
          fab
          x-small
          color="primary"
          @click="changeNum(item.id, -1)"
          :disabled="Number(item.num) == 0"
        >
          <v-icon dark>mdi-minus</v-icon>
        </v-btn>
        <v-btn class="mx-2" fab x-small color="primary" @click="changeNum(item.id, 1)">
          <v-icon dark>mdi-plus</v-icon>
        </v-btn>
      </template>
      <template v-slot:body.append>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>{{countSum}}</td>
          <td>
            <v-btn small color="#B2DFDB" :disabled="countSum == 0" @click="generateOrder">结算订单</v-btn>
          </td>
        </tr>
      </template>
    </v-data-table>
    <v-dialog v-model="payDialog.show" max-width="290">
      <v-card>
        <v-card-title class="headline">扫码支付</v-card-title>
        <v-card-text>{{payDialog.payUrl}}</v-card-text>
      </v-card>
    </v-dialog>
  </v-content>
</template>
<script>
import * as menuApi from "@/base/api/menu.js";
import querystring from "querystring";
import { systemConfig }  from '@/base/config/system'
var apiUrl = systemConfig.apiUrl
export default {
  data() {
    return {
      payDialog: {
        show: false,
        payUrl: ""
      },
      table: {
        headers: [
          {
            text: "ID",
            value: "id"
          },
          {
            text: "名称",
            value: "name"
          },
          { text: "菜系", value: "type" },
          { text: "价格", value: "price" },
          { text: "数量", value: "num" },
          { text: "总价", value: "sum", align: "center" },
          { text: "操作", value: "actions", align: "center" }
        ],
        desserts: [],
        perPage: 999,
        loading: true
      },
      menuType: []
    };
  },
  mounted() {
    this.getMenuType();
  },
  computed: {
    countSum() {
      var items = this.table.desserts;
      var sum = 0;
      for (const key in items) {
        sum += Number(items[key].num) * Number(items[key].price);
      }
      return sum;
    }
  },
  watch: {
    "payDialog.show"(newVal, olaVal) {
      var timer = null;
      var count = 100;
      var orderId = this.payDialog.orderId;
      if (newVal == true) {
        timer = setInterval(() => {
          count--;
          if (count == 0) {
            clearInterval(timer);
          }
          menuApi.getOrderById(orderId).then(result => {
            if (result.success && result.data.order.status == 1) {
              clearInterval(timer);
              this.payDialog.show = false;
              this.$snackbar.success("支付成功");
              //   this.getMenu();
            }
          });
        }, 2000);
      }
    }
  },

  methods: {
    menuNumReset() {
      var list = this.table.desserts;
      for (const key in list) {
        list[key].num = 0;
        this.$set(this.table.desserts, key, list[key]);
      }
    },

    generateOrder() {
      var order = {};
      order.userId = querystring.parse(window.localStorage.getItem("user")).id;
      order.sum = this.countSum;
      console.log(this.table.desserts);
      order.orderDetails = this.table.desserts.filter((item) => item.num != 0);
      console.log(order.orderDetails);
      for (const key in order.orderDetails) {
        order.orderDetails[key].menuId = order.orderDetails[key].id;
        order.orderDetails[key].menuName = order.orderDetails[key].name;
        order.orderDetails[key].sum =
          order.orderDetails[key].num * order.orderDetails[key].price;
      }
      
      menuApi
        .generateOrder(order)
        .then(result => {
          if (result.success) {
            // 生成订单成功
            var orderId = result.data.orderId;
            this.payDialog.orderId = orderId;
            this.payDialog.payUrl =
              apiUrl+"/order/" + orderId + "/pay";
            this.$snackbar.success(result.message);
            this.payDialog.show = true;
          } else {
            this.$snackbar.error(result.message);
          }
          this.menuNumReset();
        })
        .catch(error => {
          this.$snackbar.error(error.message);
        });
    },
    changeNum(id, num) {
      var list = this.table.desserts;
      for (const key in list) {
        if (list[key].id == id) {
          list[key].num = Number(list[key].num) + Number(num);
          this.$set(this.table.desserts, key, list[key]);
          break;
        }
      }
    },
    getMenuType() {
      menuApi
        .listAllMenuType()
        .then(result => {
          if (result.success) {
            var list = result.data.list;
            for (const key in list) {
              this.menuType[list[key].id] = list[key].name;
            }
            this.getMenu();
          } else {
            this.$snackbar.error(result.message);
          }
        })
        .catch(error => {
          this.$snackbar.error(error.message);
        });
    },
    getMenu() {
      menuApi
        .listAllMenu()
        .then(result => {
          if (result.success) {
            this.table.desserts = result.data.list;
            for (var key in this.table.desserts) {
              this.table.desserts[key].num = 0;
            }
          } else {
            this.$snackbar.error(result.message);
          }
          this.table.loading = false;
        })
        .catch(error => {
          this.table.loading = false;
          this.$snackbar.error(error.message);
        });
    }
  }
};
</script>