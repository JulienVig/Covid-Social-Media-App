<template>
      <app-navigator></app-navigator>
</template>

<script>
import LoginScreen from "./components/Screens/LoginScreen";
import DetailsScreen from "./components/Screens/DetailsScreen";
import SettingsScreen from "./components/Screens/SettingsScreen";
import ChallengesScreen from "./components/Screens/ChallengesScreen";
import ValidationChallenge from "./components/Screens/ValidationChallenge";//temporary

import {
  createAppContainer,
  createBottomTabNavigator,
  createMaterialTopTabNavigator,
  createStackNavigator
} from "vue-native-router";


const Tabs = createBottomTabNavigator(
  {

    Challenges: ChallengesScreen,
    Details: DetailsScreen,
    Settings: SettingsScreen,
    Validation: ValidationChallenge, //temporary
  }
);
const StackNavigator = createStackNavigator(
  {
    Home: LoginScreen,
    Tabs
  }
);
const AppNavigator = createAppContainer(StackNavigator);
export default {
  components: { AppNavigator }
}
</script>
