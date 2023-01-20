<template>
  <div v-if="android">
    <v-list>
      <v-list-item
        :key="i"
        @click.prevent.stop="menuClick($event, i)"
        v-for="(i, index) in 25"
        :title="`Dieses Element hat den Index ${index}`"
      />
      <vue-simple-context-menu
        element-id="androidMenu"
        :options="options"
        ref="contextMenu"
      />
    </v-list>
  </div>
  <div v-if="ios">
    <p>iOS</p>
  </div>
</template>

<script>
export default {
  name: "InteractionDesignView",
  data() {
    return {
      android: false,
      ios: false,
      options: [
        {
          name: "Delete",
          slug: "delete",
        },
      ],
    };
  },
  methods: {
    longPress() {
      console.log("detect");
    },
    menuClick(event, item) {
      this.$refs.contextMenu.showMenu(event, item);
    },
  },
  mounted() {
    let userAgent = window.navigator.userAgent,
      platform = window.navigator.platform,
      iosPlatforms = ["iPhone", "iPad", "iPod"];

    if (iosPlatforms.indexOf(platform) !== -1) {
      this.ios = true;
    } else if (/Android/.test(userAgent)) {
      this.android = true;
    }
  },
};
</script>

<style scoped></style>
