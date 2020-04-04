<template>
  <view class="container">
  <!--
    Now we provide each todo-item with the todo object
    it's representing, so that its content can be dynamic.
    We also need to provide each component with a "key",
    which will be explained later.
  -->
    <todo-item
      class="text-container"
      v-for="todo in todos"
      :key="todo.text"
      :item="todo"
    />
    <text-input>It happened !!</text-input>
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
import TodoItem from "./todoItem";
export default {
  components: { TodoItem },
  data: function() {
    return {
      todos: [
        { id: 1, text: "Learn JavaScript" },
        { id: 2, text: "Learn Vue" },
        { id: 3, text: "Build something awesome" }
      ]
    };
  }
};
</script>