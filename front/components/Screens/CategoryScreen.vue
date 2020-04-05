<template>
    <view class="real-container">
        <view class="container">
            <view class="header">
              <text class="title">Catégories</text>
            </view>
            <view class ="main">
                <view class="categories">
                    <!-- <scroll-view class = "scrollable"> -->
                        <view class="category" v-for="(category, id) in categories" :key="id">
                            <view class="single-element-container" :on-press="goToCategory(category.name)">
                                <image class = "icon" :source="require('../../assets/images/defiscreen/licorne.png')"/>
                                <text class="name">{{category.name}}</text>
                            </view>
                        </view>
                    <!-- </scroll-view> -->
                </view>
            </view>
        </view>
  </view>
</template>

<style>

.header{
    background-color: #DDDDDD;
    width: 100%;
    justify-content: center;
    align-items: center;
}

.title{
    background-color: #888888;
}

.icon {
  height:80;
  width:80;
  /* border-radius: 50%; */
}

.main {
    justify-content: center;
    align-items: center;
}
.categories{

    /* margin: 10px; */

    flex-direction: row;
    justify-content: space-around;
    flex-wrap: wrap;
    max-width: 90%;
}
.scrollable{
    background-color: #EEEEEE;
    flex-direction: row;
}
.single-element-container{
    justify-content: center;
    align-items: center;
    margin: 10px;
    max-width: 100px;
    width: 90%;
}

.name{
    justify-content: center;
    align-items: center;
}

.real-container {
    /* align-items: center;
    justify-content: center;
    flex:1; */
}
.container {
  background-color: grey;
  width:100%;
}

.title{
    font-size: 40;
    font-weight: 100;
    margin-bottom: 10;
}

</style>

<script>
import {API} from '../../api.js';
import { Alert } from 'react-native';
import axios from "axios";

export default {
    props: {
      navigation: {
        type: Object
      }
    },
  data: function() {
    return {
        categories: [
            {
                name: "Jardinage de tulipe",
                logo:"",
                logoPath:"",
            },
            {
                name: "Dessin",
                logo:"",
                logoPath:"",
            },
            {
                name: "Bricolage",
                logo:"",
                logoPath:"",
            },
            {
                name: "Musique",
                logo:"",
                logoPath:"",
            },
            {
                name: "Entraide",
                logo:"",
                logoPath:"",
            },
            {
                name: "Nettoyage",
                logo:"",
                logoPath:"",
            },
            {
                name: "Jardinage de tulipe",
                logo:"",
                logoPath:"",
            },
            {
                name: "Dessin",
                logo:"",
                logoPath:"",
            },
            {
                name: "Bricolage",
                logo:"",
                logoPath:"",
            },

        ]
    }
  },
  methods: {
    fetch () {
     console.log("Login !")
        const self = this;
       API({
        method: 'get',
        url: '/api/allCategories',
        }).then(function(response){
            self.categories = response.data;
            // this.setLogos();
            console.log("==================       GET ALL CATEGORIES       ==================");
            console.log(response)

        }).catch(function(error){
            console.log("==================      ERROR       ==================")
           console.log(error)
        })
    },
    setLogos(){

    },
    goToCategory(name) {
      // this.navigation.navigate("Challenges")
    },

  },
  mounted: function() {
      fetch();
  }
};
</script>
