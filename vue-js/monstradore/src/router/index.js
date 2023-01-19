import { createRouter, createWebHistory } from "vue-router";
import OverviewView from "../views/OverviewView.vue";

const routes = [
  {
    path: "/",
    name: "overview",
    component: OverviewView,
  },
  {
    path: "/0",
    name: "uielements",
    component: () => import("../views/AboutView.vue"),
  },
  {
    path: "/1",
    name: "interaction",
    component: () => import("../views/AboutView.vue"),
  },
  {
    path: "/2",
    name: "gestures",
    component: () => import("../views/AboutView.vue"),
  },
  {
    path: "/3",
    name: "navigation",
    component: () => import("../views/NavigationView.vue"),
  },
  {
    path: "/4",
    name: "input",
    component: () => import("../views/InputMethodsView.vue"),
  },
  {
    path: "/5",
    name: "multimedia",
    component: () => import("../views/MultimediaView.vue"),
  },
  {
    path: "/6",
    name: "animations",
    component: () => import("../views/AboutView.vue"),
  },
  {
    path: "/7",
    name: "objects",
    component: () => import("../views/AboutView.vue"),
  },
  {
    path: "/13",
    name: "gps",
    component: () => import("../views/GPSView.vue"),
  },
  {
    path: "/14",
    name: "acceleration",
    component: () => import("../views/AccelerationView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
