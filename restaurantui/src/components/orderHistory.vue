<template>
  <v-content>
    <!-- @item-expanded="o => listOrderDetailsByOrderId(o.item.id)" -->
    <v-data-table
      :headers="table.headers"
      :items="table.desserts"
      :items-per-page="table.perPage"
      class="elevation-1"
      :loading="table.loading"
      hide-default-footer
      show-expand
      single-expand
      @item-expanded="o => o.value == true?listOrderDetailsByOrderId(o.item.id):''"
    >
      <template v-slot:item.createTime="{ item }">{{item.createTime.replace('T',' ').substr(0,19)}}</template>
      <template v-slot:item.status="{ item }">
        <v-chip v-if="item.status == 0" color="error" @click="openPayPage(item.id)">未支付</v-chip>
        <v-chip v-else-if="item.status == 1" color="success">已支付</v-chip>
      </template>
      <template v-slot:expanded-item="{ headers }">
        <td :colspan="headers.length" class="pa-0">
          <v-data-table
            :headers="table.expanded.headers"
            :items="table.expanded.desserts"
            :loading="table.expanded.loading"
            hide-default-footer
          ></v-data-table>
        </td>
      </template>
    </v-data-table>
  </v-content>
</template>
<script>
import * as menuApi from "@/base/api/menu.js";
import querystring from "querystring";
import { systemConfig } from "@/base/config/system";
var apiUrl = systemConfig.apiUrl;
export default {
  data() {
    return {
      payDialog: {
        show: false,
        payUrl: ""
      },
      table: {
        expanded: {
          desserts: [
            {
              menuName: "烧鸡",
              price: 123,
              num: 1,
              sum: 123
            }
          ],
          headers: [
            { text: "名称", value: "menuName" },
            { text: "价格", value: "price" },
            { text: "数量", value: "num" },
            { text: "总价", value: "sum" }
          ]
        },
        headers: [
          {
            text: "订单号",
            value: "id"
          },
          { text: "总价", value: "sum" },
          { text: "下单时间", value: "createTime" },
          { text: "状态", value: "status" }
        ],
        desserts: [],
        perPage: 10,
        loading: true
      },
      menuType: []
    };
  },
  mounted() {
    this.listAllOrderByUserId();
  },

  methods: {
    openPayPage(orderId) {
      window.open(apiUrl + "/order/" + orderId + "/pay", "_blank");
    },
    listAllOrderByUserId() {
      menuApi
        .listAllOrderByUserId(this.$root.$data.userId)
        .then(result => {
          if (result.success) {
            this.table.desserts = result.data.list;
          } else {
            this.$snackbar.error(result.message);
          }
          this.table.loading = false;
        })
        .catch(error => {
          this.table.loading = false;
          this.$snackbar.error(error.message);
        });
    },
    listOrderDetailsByOrderId(orderId) {
        this.table.expanded.desserts = [];
      this.table.expanded.loading = true;
      menuApi
        .listOrderDetailsByOrderId(orderId)
        .then(result => {
          if (result.success) {
            this.table.expanded.desserts = result.data.list;
          } else {
            this.$snackbar.error(result.message);
          }
          this.table.expanded.loading = false;
        })
        .catch(error => {
          this.table.expanded.loading = false;
          this.$snackbar.error(error.message);
        });
    }
  }
};
</script>