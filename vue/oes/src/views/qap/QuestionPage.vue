<template>
  <div class="questionPage">
    <div style="box-shadow: 0 10px 10px rgba(0, 0, 0, .12)">
      <div class="header">
      <div class="title">江苏一博士“虎爸”拳脚相加逼六七岁儿女学高数。如何评价这位父亲的做法？</div>
      <div class="author">
        <el-avatar shape="square"
                   style="margin: 0 10px"
                   :size="30" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        夜空中最亮的路灯&nbsp;&nbsp;
      </div>
      <div class="content">
        12月14日，据南京市中级人民法院消息，南京一位“虎爸”的教育方式有点激进，让一年级的儿子和幼儿园的女儿学中学、大学知识，学到深夜还辱骂加殴打，无奈之下，孩子母亲只能向法院“求援”。近日，南京市建邺区人民法院处理了这起因家庭教育方式失当引发的纠纷。

        如何评价这位父亲的做法？
      </div>
      <div class="header-footer">
        <div style="color: #8590A6; font-size: x-small">
          <span>
            <i class="el-icon-s-data"></i>
            被浏览 1,375,569
          </span>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <span>
            <i class="el-icon-chat-square"></i>
            23条回答
          </span>
        </div>

      </div>

    </div>
    </div>
    <div class="answer-list">
      <div class="answers">
        <div style="width: 95%; margin: 0 auto">
          <div class="space-between">
            <div style="color: #121212; font-size: small; font-weight: bold">2,591 个回答</div>
            <el-dropdown>
              <div class="sort">
                默认排序
                <i class="el-icon-arrow-down"></i>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>默认排序</el-dropdown-item>
                <el-dropdown-item>时间排序</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <answer v-for="obj in number" :key="number"></answer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Answer from "@/views/qap/Answer";
export default {
  name: "QuestionPage",
  components: {
    Answer
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
.sort:hover{
  cursor: pointer;
}
.sort{
  font-size: x-small; color: #9498BA;

}
.space-between{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 20px;
  padding-bottom: 10px;
  border-bottom: 2px solid #F9F9F9;
}
.answers{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 900px;
  margin:20px auto 0 auto;
  background-color: #FFFFFF;
}
.answer-list{
  overflow: hidden;
  margin-bottom: -50px;
  padding-bottom: 20px;
  background-color: #F6F6F6;
}

.author {
  font-size: x-small;
  display: flex;
  align-items: center;
  margin: 10px auto 5px 0;
  /*background-color: #F6F6F6;*/
  padding: 10px 0;
  /*border-radius: 5px;*/
  /*border: 1px solid #EBEBEB;*/
}

.header {
  width: 900px;
  padding-top: 50px;
  margin: 0px auto 0px auto;
  padding-bottom: 20px;
}

.header-footer {
  display: flex;
  margin-top: 20px;
  /*height: 30px;*/
  align-items: center;
  justify-content: space-between;
}

.content {
  font-size: x-small;
  line-height: 22px;
  width: 800px;
}

.title {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  font-size: 23px;
  font-weight: bold;
}

.questionPage {
}
</style>