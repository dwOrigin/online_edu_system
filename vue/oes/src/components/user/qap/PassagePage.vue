<template>
  <div style="overflow: hidden; margin-bottom: -50px">
    <div class="passage-page">
      <div class="title">{{ passage.title }}</div>
      <div class="time"> {{ passage.time }} 修改</div>
      <div class="content">
        {{ passage.content}}
      </div>
      <div class="footer">
        <div>
          <el-button
              @click="handleClick('like')"
              type="primary" plain>点赞({{passage.like}})</el-button>
        </div>
        <div>
          <el-button
              @click="handleClick('comment')"
              ype="primary" plain>评论文章</el-button>
        </div>
      </div>
      <div>
        <div style="margin-top: 10px">
          <answer v-for="obj in passage.comments" :obj="obj"></answer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Answer from "@/components/user/qap/Answer";

export default {
  name: "PassagePage",
  components: {
    Answer
  },
  data() {
    return {
      number: 10,
      passage: {}
    };
  },
  created() {
    window.addEventListener('scroll', this.scrollBottom);
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollBottom) //页面离开后销毁监听事件
  },
  methods: {
    loadPassage() {
      let id = this.$route.query.pId;
      //获取文章详细信息
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{
      //       pId: id
      //     }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            passage: {
              title: '文章标题',
              time: '2022/12/26',
              like: '18, 888',
              content: '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容这是' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容',
              comments: [
                {
                  commenterName: '评论者名字',
                  commenterAvatarUrl: 'https://pic4.zhimg.com/v2-d41c2ceaed8f51999522f903672a521f_xs.jpg?source=1940ef5c',
                  content: '这是评论内容',
                  time: '2022/8/71',
                  like: '9, 999'
                }
              ]
            }
          }
        });
      });
      promise.then((res) => {
        this.passage = res.data.passage;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    scrollBottom() {
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      let clientHeight = document.documentElement.clientHeight;
      let scrollHeight = document.documentElement.scrollHeight;
      if (scrollTop + clientHeight >= scrollHeight) {
        this.number += 3;
      }
    },
    handleClick(btnName){

    }
  },
  mounted() {
    this.loadPassage();
  }
}
</script>

<style scoped>
.content {
  word-wrap: break-word;
  margin: 10px 20px;
}

.footer {
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
  margin: 0 0 30px 0;
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