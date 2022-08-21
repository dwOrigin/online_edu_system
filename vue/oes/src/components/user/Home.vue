<template>
<div>
  <navigation></navigation>
  <HomeMain></HomeMain>
  <home-footer></home-footer>
</div>
</template>

<script>
import Navigation from './home/Navigation';
import HomeMain from "@/components/user/home/HomeMain";
import HomeFooter from "@/components/user/home/HomeFooter";
export default {
  name: "Home",
  methods: {
  },
  components:{
    Navigation,
    HomeMain,
    HomeFooter
  },
  mounted() {
    this.$router.replace({
    })
  }
}

</script>

<style scoped>
</style>