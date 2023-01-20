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
    component: () => import("../views/UIElementsView.vue"),
  },
  {
    path: "/0/android",
    name: "androidelements",
    component: () => import("../views/AndroidElementsView.vue"),
  },
  {
    path: "/0/ios",
    name: "ioselements",
    component: () => import("../views/IOSElementsView.vue"),
  },
  {
    path: "/1",
    name: "interaction",
    component: () => import("../views/InteractionDesignView.vue"),
  },
  {
    path: "/2",
    name: "gestures",
    component: () => import("../views/GesturesView.vue"),
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
    component: () => import("../views/AnimationsView.vue"),
  },
  {
    path: "/7",
    name: "objects",
    component: () => import("../views/ObjectsView.vue"),
  },
  {
    path: "/8",
    name: "networkcall",
    component: () => import("../views/NetworkcallView.vue"),
  },
  {
    path: "/9",
    name: "fileaccess",
    component: () => import("../views/FileAccessView.vue"),
  },
  {
    path: "/10",
    name: "persistence",
    component: () => import("../views/PersistenceView.vue"),
  },
  {
    path: "/11",
    name: "appaccess",
    component: () => import("../views/EmptyView.vue"),
  },
  {
    path: "/12",
    name: "camera",
    component: () => import("../views/CameraView.vue"),
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
  {
    path: "/15",
    name: "fingerprint",
    component: () => import("../views/EmptyView.vue"),
  },
  {
    path: "/16",
    name: "prime",
    component: () => import("../views/PerformanceView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
