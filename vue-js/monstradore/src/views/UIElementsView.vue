<template>
  <h1>Reichhaltige UI Elemente</h1>
  <h2>Grundlegende Elemente</h2>
  <h3>Text</h3>
  <p>
    Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
    eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam
    voluptua.
  </p>
  <h3>Bild</h3>
  <img :src="require(`../assets/sample.jpeg`)" alt="Sample Image" />
  <h3>Liste</h3>
  <ul>
    <li :key="i" v-for="(i, index) in 5">Item: {{ index }}</li>
  </ul>
  <h3>Button</h3>
  <v-btn>Button</v-btn>
  <h3>Icon Button</h3>
  <v-btn icon="mdi-home" />
  <h2>Elemente mit Statusverwaltung</h2>
  <h3>Slider</h3>
  <v-slider />
  <h3>Textfeld</h3>
  <v-text-field />
  <h3>Switch / Toggle</h3>
  <v-switch v-model="switchVal" color="info" />
  <h2>Plattformspezifische Elemente</h2>
  <div>
    <nav>
      <router-link :to="{ path: '/0/android' }"
        ><v-btn>Android</v-btn></router-link
      >
    </nav>
    <router-view />
    <nav>
      <router-link :to="{ path: '/0/ios' }"><v-btn>iOS</v-btn></router-link>
    </nav>
    <router-view />
  </div>
  <h2>Fortgeschrittene Elemente</h2>
  <v-btn @click.prevent.stop="menuClick($event, menu)"> {{ menu.name }}</v-btn>
  <vue-simple-context-menu
    element-id="myMenu"
    :options="options"
    ref="contextMenu"
  />
  <v-dialog v-model="dialog">
    <template v-slot:activator="{ props }">
      <v-btn v-bind="props"> Dialog </v-btn>
    </template>

    <v-card>
      <v-card-title>Dialog Title</v-card-title>
      <v-card-text>Dialog Text</v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn text @click="dialog = false">Cancel</v-btn>
        <v-btn text @click="dialog = false">OK</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import VueSimpleContextMenu from "vue-simple-context-menu";
export default {
  components: {
    VueSimpleContextMenu,
  },
  data() {
    return {
      name: "UIElementsView",
      switchVal: false,
      options: [
        {
          name: "Menu",
          slug: "menu",
        },
      ],
      menu: {
        name: "Menu",
      },
      dialog: false,
    };
  },
  methods: {
    menuClick(event, item) {
      this.$refs.contextMenu.showMenu(event, item);
    },
  },
};
</script>

<style>
@import "../../node_modules/vue-simple-context-menu/dist/vue-simple-context-menu.css";
</style>
