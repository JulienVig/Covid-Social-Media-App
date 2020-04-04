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
.h1 {
  color: #eeaf67;
}
.h2 {
  color: #e7794c;
}
.h3 {
  color: #980740;
}
.h4 {
  color: #f9b7e0;
}
.h5 {
  color: #0d5b65;
}
.h6 {
  color: #1d3060;
}
.h7 {
  color: #3d9d84;
}
.h8 {
  color: #65d498;
}
.h1_l {
  color: #f1b9a2;
}
.h2_l {
  color: #f6ddc0;
}
.h4_l {
  color: #fedff2;
}
.h5_l {
  color: #7ebcc4;
}
.h6_l {
  color: #7384ad;
}
.h7_l {
  color: #7fc7b4;
}
.h8_l {
  color: #b2ebcc;
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
