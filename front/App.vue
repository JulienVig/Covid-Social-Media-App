<template>
  <view class ="main-container">
    <view class="navigation-padding"/>
    <Navigation/>
  </view>
</template>

<style>
  .main-container {
    flex : 1;
  }
  .navigation-padding {
    
  }
</style>

<script>
import Navigation from './navigation.js';

export default {
  components: { Navigation }
}
</script>
