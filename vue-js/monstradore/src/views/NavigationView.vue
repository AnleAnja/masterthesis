<template>
  <vue-tabs>
    <v-tab title="Tab 1" @click="setTop(1)" />
    <v-tab title="Tab 2" @click="setTop(2)" />
    <v-tab title="Tab 3" @click="setTop(3)" />
  </vue-tabs>
  <h2>Tab {{ top }}</h2>
  <h2>Item {{ bottom }}</h2>
  <v-bottom-navigation>
    <v-btn @click="setBottom(1)">
      <span> Item 1 </span>
      <v-icon>mdi-home</v-icon>
    </v-btn>
    <v-btn @click="setBottom(2)">
      <span> Item 2 </span>
      <v-icon>mdi-home</v-icon>
    </v-btn>
    <v-btn @click="setBottom(3)">
      <span> Item 3 </span>
      <v-icon>mdi-home</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  data() {
    return {
      bottom: 1,
      top: 1,
      name: "NavigationView",
    };
  },
  methods: {
    setTop(i) {
      this.top = i;
    },
    setBottom(i) {
      this.bottom = i;
    },
  },
};
</script>

<style scoped></style>
