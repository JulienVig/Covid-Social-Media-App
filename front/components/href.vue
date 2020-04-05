<template>
    <touchable-opacity class = "link" :on-press="() => click()">
        <text class= "link">{{displayedLink}}</text>
    </touchable-opacity>
</template>

<style>
    .link {
        color: blue;
    }
</style>

<script>
import {Linking} from "react-native"
export default {
  props: ['url', 'name'],
  data: function() {
    return {
      displayedLink: (this.name === undefined) ? this.url : this.name
    }
  },
  methods: {
    click () {
      Linking.openURL(this.url)
    },
  },
  computed: {
    computeDisplay() {
      displayedLink = (name === null) ? url : name
    }
  }
};
</script>
