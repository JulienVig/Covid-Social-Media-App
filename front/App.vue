<template>
      <app-navigator></app-navigator>
</template>

<script>
import LoginScreen from "./components/Screens/LoginScreen";
import CoronaScreen from "./components/Screens/CoronaScreen";
import DetailsScreen from "./components/Screens/DetailsScreen";
import ProfileScreen from "./components/Screens/ProfileScreen";
import ChallengesScreen from "./components/Screens/ChallengesScreen";
import ChallengeDetailScreen from "./components/Screens/ChallengeDetailScreen";
import CategoryScreen from "./components/Screens/CategoryScreen";

import {
  createAppContainer,
  createBottomTabNavigator,
  createMaterialTopTabNavigator,
  createStackNavigator
} from "vue-native-router";


const Tabs = createBottomTabNavigator(
  {

    Challenges: ChallengesScreen,
    Category: CategoryScreen,
    Details: DetailsScreen,
    Profile: ProfileScreen,
  }
);
const StackNavigator = createStackNavigator(
  {
    Home: LoginScreen,
    ChallengeDetail : ChallengeDetailScreen,
    Corona: CoronaScreen,
    Tabs
  }
);
const AppNavigator = createAppContainer(StackNavigator);
export default {
  components: { AppNavigator }
}
</script>
