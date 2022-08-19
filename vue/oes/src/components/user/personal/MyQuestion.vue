<template>
<div class="my-question">
  <question-record v-for="obj in number" :key="obj">
  </question-record>
</div>
</template>

<script>
import QuestionRecord from "@/components/user/qap/QuestionRecord";
export default {
  name: "MyQuestion",
  components:{
    QuestionRecord
  },
  data(){
      return {
          number: 10
      };
  },
  created() {
    window.addEventListener('scroll', this.scrollBottom);
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollBottom) //页面离开后销毁监听事件
  },
  methods: {
    scrollBottom(){
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      let clientHeight = document.documentElement.clientHeight;
      let scrollHeight = document.documentElement.scrollHeight;
      if (scrollTop + clientHeight >= scrollHeight) {
        this.number += 3;
      }
    }
  }
}
</script>

<style scoped>
.my-question{
  min-height: 600px;
}
</style>