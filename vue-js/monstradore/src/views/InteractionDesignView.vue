<template>
  <div v-if="android">
    <v-list>
      <v-list-item
        :key="item"
        @mousedown.prevent.stop="start($event, item)"
        @click.prevent.stop="cancel"
        @mouseout.prevent.stop="cancel"
        v-for="(item, index) in 25"
        :title="`Dieses Element hat den Index ${index}`"
      />
      <vue-simple-context-menu
        element-id="androidMenu"
        class="contextMenu"
        :options="options"
        ref="contextMenu"
        @mouseup.prevent.stop="test"
        @click.prevent.stop="test"
      />
    </v-list>
  </div>
  <div v-if="ios">
    <v-list>
      <v-list-item
        :key="item"
        @mousedown.prevent.stop="swipeToDismissStart($event, index)"
        @mousemove.prevent.stop="swipeToDismiss($event)"
        @mouseup.prevent.stop="swipeToDismissEnd"
        v-for="(item, index) in 25"
        :title="`Dieses Element hat den Index ${index}`"
      />
    </v-list>
  </div>
</template>

<script>
import VueSimpleContextMenu from "vue-simple-context-menu";
export default {
  name: "InteractionDesignView",
  components: {
    VueSimpleContextMenu,
  },
  data() {
    return {
      android: false,
      ios: true,
      longPressTimeOut: null,
      startPosition: 0,
      mouseDown: false,
      deleteIndex: 0,
      options: [
        {
          name: "Delete",
          slug: "delete",
        },
      ],
    };
  },
  methods: {
    test() {
      console.log("clicked");
    },
    start(event, item) {
      this.longPressTimeOut = setTimeout(() => {
        this.longPressTimeOut = null;
        this.$refs.contextMenu.showMenu(event, item);
      }, 500);
    },
    cancel() {
      if (this.longPressTimeOut !== null) {
        clearTimeout(this.longPressTimeOut);
        this.longPressTimeOut = null;
      }
    },
    swipeToDismissStart(event, index) {
      this.startPosition = event.screenX;
      this.mouseDown = true;
      this.deleteIndex = index;
    },
    swipeToDismiss(event) {
      if (this.mouseDown && event.screenX - this.startPosition < -50) {
        console.log("swipe");
        this.swipeToDismissEnd();
      }
    },
    swipeToDismissEnd() {
      this.mouseDown = false;
      this.startPosition = 0;
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

<style scoped>
@import "../../node_modules/vue-simple-context-menu/dist/vue-simple-context-menu.css";
.contextMenu {
  margin: 30px !important;
}
</style>
