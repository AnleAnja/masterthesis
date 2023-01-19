<template>
  <h1>Struktur und Navigation</h1>
  <h2>App Bar Top</h2>
  <v-app-bar>
    <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
    <v-app-bar-title>Top App Bar</v-app-bar-title>
  </v-app-bar>
  <p style="color: deepskyblue">Verfügbar, s.o.</p>
  <h2>App Bar Bottom</h2>
  <p style="color: darkred">
    Nur mit Navigation verfügbar, siehe Feature "Navigation"
  </p>
  <h2>Navigation Drawer</h2>
  <v-navigation-drawer v-model="drawer" absolute temporary>
    <div>Navigation Drawer Content</div>
  </v-navigation-drawer>
  <p style="color: deepskyblue">Verfügbar, s.o.</p>
  <h2>Navigation Rail</h2>
  <p style="color: darkred">Nicht nativ verfügbar</p>
  <h1>Grundlegende Android Elemente</h1>
  <h2>Card</h2>
  <v-card style="width: 150px">
    <v-card-text>Card Content</v-card-text>
  </v-card>
  <h2>Chip</h2>
  <v-chip>Chip Content</v-chip>
  <h2>Snackbar</h2>
  <v-btn @click="snackbar = true">Snackbar</v-btn>
  <v-snackbar v-model="snackbar">Snackbar Content</v-snackbar>
  <h2>Segmented Buttons</h2>
  <segmented-control
    :options="options"
    label="label"
    value="value"
    color="#fff"
    active-color="#333"
    :multiple="false"
  />
  <h2>Floating Action Buttons</h2>
  <v-btn fab rounded>
    <v-icon>mdi-home</v-icon>
  </v-btn>
  <h1>Interaktive Android Elemente</h1>
  <h2>Checkbox</h2>
  <v-checkbox />
  <h2>Radio Buttons</h2>
  <v-radio-group row>
    <v-radio value="radio-1" />
    <v-radio value="radio-2" />
  </v-radio-group>
  <h2>Time Picker</h2>
  <p style="color: darkred">Nicht nativ verfügbar</p>
  <h2>Date Picker</h2>
  <p style="color: darkred">Nicht nativ verfügbar</p>
</template>

<script>
import SegmentedControl from "vue-segmented-control";

export default {
  components: {
    SegmentedControl,
  },
  data() {
    return {
      snackbar: false,
      name: "AndroidElementsView",
      options: [
        { label: "Item 1", value: "item1" },
        { label: "Item 2", value: "item2" },
        { label: "Item 3", value: "item3" },
      ],
      row: null,
      time: null,
      modalT: false,
      modalD: false,
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
    };
  },
};
</script>

<style scoped></style>
