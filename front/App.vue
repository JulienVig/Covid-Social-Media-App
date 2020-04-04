<template>
  <view class="container">
  <!--
    Now we provide each todo-item with the todo object
    it's representing, so that its content can be dynamic.
    We also need to provide each component with a "key",
    which will be explained later.
  -->
    <loginPage
    />
  </view>
</template>


<style>
.container {
  background-color: white;
  align-items: center;
  justify-content: center;
  flex: 1;
}
.text-container {
  color: blue;
  padding: 2;
  font-size: 22;
}

.text-input-container {
  width: 300;
  height: 40;
  font-size: 22;
  border-color: gray;
}
</style>

<script>
import loginPage from "./components/loginPage";
export default {
  components: { 'loginPage' : loginPage },
  data: function() {
    return {

    };
  }
};
</script>
