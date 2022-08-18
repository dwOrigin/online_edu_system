<template>
  <div class="qa-recommend">
    <div class="qa-part">
      <el-tabs type="border-card" class="hot-list">
        <el-tab-pane>
          <div slot="label" class="title-tag">问题</div>
          <QuestionCard v-for="obj in number" :key="obj"></QuestionCard>
        </el-tab-pane>
        <el-tab-pane>
          <div slot="label" class="title-tag">文章</div>
          <QuestionCard v-for="obj in number" :key="obj" type="passage"></QuestionCard>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="ask-part">
      <div>
        <el-button type="primary" round plain>
          <i class="el-icon-question"></i>
          我的提问
        </el-button>
      </div>
      <div style="margin-top: 20px">
        <el-button type="primary" round plain>
          <i class="el-icon-edit"></i>
          提出问题
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import QuestionCard from "@/components/user/qap/QuestionCard";

export default {
  name: "QAPMain",
  components: {
    QuestionCard
  },
  data() {
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
    scrollBottom() {
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
.hot-list {
  height: max-content;
  overflow: hidden;
  /*background-color: teal;*/
}

.title-tag {
  padding: 0 145px;
}

.qa-part {
  width: 72%;
  border-radius: 5px;
  padding: 5px;
}

.ask-part {
  /*overflow: visible;*/
  width: 27%;
  height: 500px;
  /*background-color: green;*/
  position: sticky;
  top: 100px;
  margin-left: 20px;
}

.qa-recommend {
  width: 1000px;
  margin: 10px auto;
  /*background-color: teal;*/
  display: flex;
  justify-content: space-between;
}
</style>