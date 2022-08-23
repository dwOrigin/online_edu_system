<template>
  <div
      @click="$router.push({
          name: 'course',
          params: {
            courseId: course.id
          }
       });"
      class="course-record" >
    <el-image
        class="radius-img"
        style="width: 200px; height: 120px"
        ref="img"
        src="https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356"
        fit="fill">
    </el-image>
    <div class="right">
      <div class="center-part">
        <div :underline="false" class="title">
          {{course.title}}
        </div>
        <div class="center-footer">
          <div v-if="type==='history'">{{course.time}}前&nbsp;&nbsp;看过&nbsp;&nbsp;</div>
          <div v-if="type==='star'">收藏于&nbsp;&nbsp; {{course.time}}</div>
          <div class="teacher">
            <el-avatar
                :size="30"
                :src="course.teacherAvatar">
              <span v-if="course.teacherAvatar === ''">{{course.teacherName}}</span>
            </el-avatar>
            &nbsp;&nbsp;&nbsp;{{course.teacherName}}
          </div>
        </div>
      </div>
      <div class="delete">
        <el-button type="info" icon="el-icon-delete" @click.stop="handleDelete" circle></el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CourseRecord",
  props: {
    type: {
      default: 'history',
      type: String
    },
    courseP: {}
  },
  data() {
    return {
      course: this.courseP
    }
  },
  methods: {
    handleDelete() {
      if(this.type === 'history'){
        //删除历史记录
        // let promise = this.$axios({
        //     url: '',
        //     method: '',
        //     data:{
        //       id: this.course.id,//历史记录id
        //       userId: this.course.userId//用户的id
        //     }
        // });
        let promise = new Promise((a)=>{
          a({
            data:{
              result: true
            }
          });
        });
        promise.then((res)=>{
          if(res.data.result){
            this.$message.success('删除成功');
            this.$bus.$emit('refreshHistory');
          }else{
            //  删除历史记录不可能失败吧?
          }
        }).catch((err)=>{
          this.$message.error('你的网络迷路了');
        });
      }
      else
        if(this.type === 'star'){
          //删除收藏记录
          // let promise = this.$axios({
          //     url: '',
          //     method: '',
          //     data:{
          //       id: this.course.id,//收藏记录id
          //       userId: this.course.userId//用户的id
          //     }
          // });
          let promise = new Promise((a)=>{
            a({
              data:{
                result: true
              }
            });
          });
          promise.then((res)=>{
            if(res.data.result){
              this.$message.success('删除成功');
              this.$bus.$emit('refreshStar');
            }else{
              //  impossible
            }
          }).catch((err)=>{
            this.$message.error('你的网络迷路了');
          });
        }
    }
  }
}
</script>

<style scoped>
.course-record:hover{
  cursor: pointer;
  transform: perspective(800px) translate3d(0,0,10px) ;
  transition: all 0.2s;
  /*border: 1px solid #409eff;*/
}
.right {
  display: flex;
  /*border-bottom: 1px solid #B3B5C0;*/
  margin: 0 20px;
}

.delete {
  margin: auto 100px;
}

.teacher {
  display: flex;
  align-items: center;
}

.center-footer {
  display: flex;
  justify-content: space-between;
  color: #A0ACBA;
  align-items: center;
  font-size: x-small;
}

.title:hover {
  color: #409EFF;
  cursor: pointer;
}

.title {
  color: #222;
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.center-part {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-grow: 0;
  flex-basis: 310px;
  flex-shrink: 0;
  margin: 0;
  padding-bottom: 5px;
}

.radius-img {
  border-radius: 10px;
  flex-grow: 0;
  flex-shrink: 0;
}

.course-record {
  display: flex;
  width: 700px;
  margin: 25px 0;
}
</style>