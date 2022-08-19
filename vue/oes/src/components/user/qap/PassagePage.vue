<template>
  <div style="overflow: hidden; margin-bottom: -50px">
    <div class="passage-page">
      <div class="title">遭老牌 Node.js、新贵 Bun “围攻”，Deno 之父紧急求变！</div>
      <div class="time"> 2022-08-18 09:52:19 修改</div>
      <div class="content">
        <p class="paragraph">
          【CSDN 编者按】截至目前，Node.js 仍然是使用最为广泛的 JavaScript 运行时环境，但是同样出自 Node.js 之父 Ryan Dahl 之手的 Deno
          却更加安全。不过近日，一款集构建、转译、依赖管理于一身的高性能 JavaScript 运行时 Bun 横空出世，在 Server、Sqlite、ffi 等方面都超过了 Node 和 Deno，似乎是迫于压力之下，Deno
          于近日宣布迎来重大改革。
        </p>
        <p class="paragraph">
          整理 | 苏宓 出品 | CSDN
        </p>
        <p class="paragraph">
          对于使用 JavaScript 的开发者而言，想必对 Node.js 并不陌生。基于 JavaScript 的 Node.js 平台是由美国知名软件工程师 Ryan Dahl 于 2009 年推出，其最初是作为
          Apache HTTP Server for Linux 和 MacOS 的更具可扩展性的替代品而开发。时至今日，Node.js 已被很多人用于生产环境。
        </p>
        <p class="paragraph">
          不过，在 2018 年，Ryan Dahl 发表了一次以「我为 Node.js 感到后悔的十件事」的主题分享，他表示在设计 Node.js 时犯一些包括安全性、package.json、node_modules
          等系列的错误，并称这些 Bug 问题严重且不可回避，于是他用了两年的时间进行了重新开发，并在 2020 年 5 月正式推出了基于 V8 引擎和 Rust 语言所创建的 JavaScript、TypeScript
          执行环境——Deno。
        </p>
        <p class="paragraph">
          然而被赋予厚望的 Deno 语言，经历了 2 年的发展，虽然在安全性等维度更胜 Node.js 一筹，但其发展似乎没有达到预期。而就在近日，一款集构建、转译、依赖管理于一身的高性能 JavaScript 运行时 Bun
          （https://bun.sh/）横空出世，甚至在其官网上，该团队分享三种技术在 Server、Sqlite、ffi 三个方面的性能对比，结果显示，初出茅庐的 Bun 远超
          node、deno，这不禁让“前辈们”感到了巨大压力。
        </p>
      </div>
      <div class="footer">
        <div>
          <el-button type="primary" plain>点赞(163,155)</el-button>
        </div>
        <div><el-button type="primary" plain>评论文章</el-button></div>
      </div>
      <div>
        <div style="margin-top: 10px">
          <answer v-for="obj in number"></answer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Answer from "@/components/user/qap/Answer";
export default {
  name: "PassagePage",
  components:{
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
.footer{
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #CCCCD8;
  font-size: small;
  /*border: 1px solid #ECECED;*/
  padding: 10px;
  border-top: 2px solid #F9F9F9;
  border-bottom: 2px solid #F9F9F9;
}
.paragraph {
  text-indent: 2em;
  margin: 0  0 30px 0;
}

.content {
  padding: 10px 20px;
  line-height: 30px;
}

.time {
  color: #999AAA;
  font-size: small;
  margin: 10px 0;
}

.title {
  font-size: x-large;
  font-weight: bold;
}

.passage-page {
  border: 1px solid #ECECED;
  width: 900px;
  /*background-color: #F0EFE2;*/
  margin: 10px auto 20px auto;
  /*border: 1px solid #DAD8C1;*/

  padding: 20px;
  /*height: 100px;*/
  /*background-color: teal;*/
}
</style>