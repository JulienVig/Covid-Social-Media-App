<template>
  <view class="container">
    <text class="text-color-primary">My Vue Native App</text>
    <my-login/>
  </view>
</template>

<style>
.container {
  background-color: white;
  align-items: center;
  justify-content: center;
  flex: 1;
}
.text-color-primary {
  color: blue;
}
</style>

<script>
import Login from "./login_page.vue";
export default {
  name: 'login',
  components: {
    'my-login': Login
  }
}
</script>