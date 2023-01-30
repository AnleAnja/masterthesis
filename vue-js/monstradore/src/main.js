import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { loadFonts } from "./plugins/webfontloader";
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import FileSelector from "vue-file-selector";
import "./registerServiceWorker";

const vuetify = createVuetify({
  components,
  directives,
  ssr: true,
});

loadFonts();

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .use(FileSelector)
  .mount("#app");
