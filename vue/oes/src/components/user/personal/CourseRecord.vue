<template>
<div class="course-record">
  <el-image
      class="radius-img"
      style="width: 200px; height: 120px"
      ref="img"
      src="https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356"
      fit="fill">
  </el-image>
  <div class="right" >
    <div class="center-part" @click="$router.push({
          name: 'course',
          params: {
            courseId: course.id
          }
       });">
      <div :underline="false" class="title" >
        这是标题这是标题这是标题这是标题这是标题这是标题这是标题这是标题这是标题
      </div>
      <div class="center-footer">
        <div v-if="type=='history'">21小时前&nbsp;&nbsp;看到&nbsp;&nbsp;06:15</div>
        <div v-if="type=='star'">收藏于&nbsp;&nbsp; 2022/08/17</div>
        <div class="teacher">
          <el-avatar
              :size="30"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          &nbsp;&nbsp;&nbsp;甘雨
        </div>
      </div>
    </div>
    <div class="delete">
      <el-button type="info" icon="el-icon-delete" @click="handledelete" circle></el-button>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "CourseRecord",
  props:{
    type:{
      default: 'history',
      type: String
    }
  },
  data(){
    return {
      course: {
      default() {
        return {
          id: 9999,
          imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
          title: '[量子速学] 一秒钟一个单词 一小时考过四级! 你值得拥有',
          teacher: 'bilbil大学',
          chapterNum: 16,
          studentNum: 10086,
          likeRate: 100
      };
      }
    }
    }
  },
  methods:{
    handledelete(){
      console.log(1);
    }
  }
}
</script>

<style scoped>
.right{
  display: flex;
  /*border-bottom: 1px solid #B3B5C0;*/
  margin: 0 20px;
}
.delete{
  margin: auto 100px;
}
.teacher{
  display: flex;
  align-items: center;
}
.center-footer{
  display: flex;
  justify-content: space-between;
  color: #A0ACBA;
  align-items: center;
  font-size: x-small;
}
.title:hover{
  color: #409EFF;
  cursor: pointer;
}
.title{
  color: #222;
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.center-part{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-grow: 0;
  flex-basis: 310px;
  flex-shrink: 0;
  margin: 0;
  padding-bottom: 5px;
}
.radius-img{
  border-radius: 10px;
  flex-grow: 0;
  flex-shrink: 0;
}
.course-record{
  display: flex;
  width: 700px;
  margin: 25px 0;
}
</style>