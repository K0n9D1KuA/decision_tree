<!--  -->
<template>
  <div class="">
    <el-row>
      <el-col :span="23"
        ><div class="grid-content bg-purple">
          <div class="title">生成决策树</div>
          <!-- 按钮 -->
          <div class="buttons">
            <el-button type="success" @click="addLeafNode()"
              >添加叶子节点</el-button
            >
            <el-button type="success" @click="addNoLeafNode()"
              >添加非叶子节点</el-button
            >
            <el-button type="success" @click="addBranch()">添加树枝</el-button>
            <el-button type="success" @click="buildDecisionTree()"
              >生成决策树</el-button
            >
          </div>
          <!--添加非叶子节点 -->
          <el-dialog
            title="添加非叶子节点"
            :visible.sync="dialogVisible"
            width="30%"
          >
            <el-form
              :label-position="labelPosition"
              label-width="80px"
              :model="noLeafNode"
            >
              <el-form-item label="判断规则">
                <el-input v-model="noLeafNode.ruleKey"></el-input>
              </el-form-item>
              <el-form-item label="结点描述">
                <el-input v-model="noLeafNode.description"></el-input>
              </el-form-item>
              <el-form-item label="层级">
                <el-input-number
                  v-model="num"
                  @change="noLeafNodeHandleChange"
                  :min="1"
                  :max="10"
                  label="层级"
                ></el-input-number>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="submitNoLeafNode()">添加</el-button>
            </span>
          </el-dialog>

          <!-- 添加树枝  -->
          <el-dialog
            title="添加树枝"
            :visible.sync="dialogVisible3"
            width="30%"
          >
            <el-form
              :label-position="labelPosition"
              label-width="80px"
              :model="branch"
            >
              <el-form-item label="从哪里来">
                <el-select v-model="branch.treeNodeFromId" placeholder="请选择">
                  <el-option
                    v-for="item in treeNodes"
                    :key="item.treeNodeId"
                    :label="item.treeNodeId"
                    :value="item.treeNodeId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="到哪里去">
                <el-select v-model="branch.treeNodeToId" placeholder="请选择">
                  <el-option
                    v-for="item in treeNodes"
                    :key="item.treeNodeId"
                    :label="item.treeNodeId"
                    :value="item.treeNodeId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="判断类型">
                <el-select v-model="branch.limitType" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="判断值">
                <el-input v-model="branch.limitValue"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="submitBranch()">添加</el-button>
            </span>
          </el-dialog>

          <!--  -->
          <!--添加叶子节点 -->
          <el-dialog
            title="添加叶子节点"
            :visible.sync="dialogVisible2"
            width="30%"
          >
            <el-form
              :label-position="labelPosition"
              label-width="80px"
              :model="leafNode"
            >
              <el-form-item label="结点描述">
                <el-input v-model="leafNode.description"></el-input>
              </el-form-item>
              <el-form-item label="层级">
                <el-input-number
                  v-model="num"
                  @change="leafNodeHandleChange"
                  :min="1"
                  :max="10"
                  label="层级"
                ></el-input-number>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="submitLeafNode()">添加</el-button>
            </span>
          </el-dialog>

          <!-- 生成决策树 -->
          <el-dialog
            title="生成决策树"
            :visible.sync="dialogVisible4"
            width="30%"
          >
            <el-form
              :label-position="labelPosition"
              label-width="100px"
              :model="treeRich"
            >
              <el-form-item label="决策树名字">
                <el-input v-model="treeRich.treeRichName"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="submitTreeRich()">添加</el-button>
            </span>
          </el-dialog>
          <div class="holder">
            <div v-for="(item, index) in treeNodes" :key="index" class="level">
              <div class="treeNode" v-if="item.level == 1">
                <div class="treeNode-left">{{ item.description }}</div>
                <div class="treeNode-right">id:{{ item.treeNodeId }}</div>
              </div>
            </div>
          </div>

          <div class="holder">
            <div v-for="(item, index) in treeNodes" :key="index" class="level">
              <div class="treeNode" v-if="item.level == 2">
                <div class="treeNode-left">{{ item.description }}</div>
                <div class="treeNode-right">id:{{ item.treeNodeId }}</div>
              </div>
            </div>
          </div>

          <div class="holder">
            <div v-for="(item, index) in treeNodes" :key="index" class="level">
              <div class="treeNode" v-if="item.level == 3">
                <div class="treeNode-left">{{ item.description }}</div>
                <div class="treeNode-right">id:{{ item.treeNodeId }}</div>
              </div>
            </div>
          </div>

          <div v-for="(item, index) in branches" :key="index" class="level1">
            <div class="treeNode">
              从{{ item.treeNodeFromId }}连接到{{
                item.treeNodeToId
              }}，判断规则是：{{ showLimitType(item.limitType)
              }}{{ item.limitValue }}
            </div>
          </div>

          <!-- <div class="holder">
            <div v-for="(item, index) in treeNodes" :key="index" class="level">
              <div class="treeNode" v-if="item.level == 4">
                <div class="treeNode-left">{{ item.description }}</div>
                <div class="treeNode-right">id:{{ item.treeNodeId }}</div>
              </div>
            </div>
          </div> -->
        </div></el-col
      >
      <el-col :span="1"
        ><div class="grid-content bg-purple-light">
          <!-- <div class="title">决策树生成预览</div> -->
        </div></el-col
      >
    </el-row>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import axios from "axios";
export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  data() {
    //这里存放数据
    return {
      dialogVisible4: false,
      dialogVisible3: false,
      branch: {},
      branches: [],
      //所有的树节点
      treeNodes: [],
      dialogVisible2: false,
      dialogVisible: false,
      //全局自增id  控制树节点的id自增
      icreasingId: 1,
      //叶子结点
      leafNode: {
        treeNodeType: 1,
        treeId: 0,
        level: 1,
      },
      noLeafNode: {
        treeNodeType: 0,
        treeId: 0,
        level: 1,
      },
      labelPosition: "right",
      num: 1,
      options: [
        {
          value: 1,
          label: "=",
        },
        {
          value: 2,
          label: ">",
        },
        {
          value: 3,
          label: "<",
        },
        {
          value: 4,
          label: ">=",
        },
        {
          value: 5,
          label: "<=",
        },
      ],
      treeRich: {},
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    showLimitType(limitType) {
      if (limitType == 1) {
        return "=";
      }
      if (limitType == 2) {
        return ">";
      }
      if (limitType == 3) {
        return "<";
      }
      if (limitType == 4) {
        return ">=";
      }
      if (limitType == 5) {
        return "<=";
      } else {
        return "默认";
      }
    },
    submitTreeRich() {
      this.treeRich.treeRootId = 1;
      this.treeRich.branches = this.branches;
      this.treeRich.treeNodes = this.treeNodes;
      this.treeRich.treeId = null;
      var _this = this;
      axios({
        method: "post",
        url: "http://localhost:9999/buildDecisionTree",
        data: _this.treeRich,
      }).then((res) => {
        this.data = res.data;
      });
      console.log(this.treeRich);
      this.$message({
        message: "添加决策树成功！",
        type: "success",
      });
    },
    buildDecisionTree() {
      this.dialogVisible4 = true;
    },
    submitBranch() {
      console.log("当前树枝的信息", this.branch);
      this.branches.push({
        treeNodeToId: this.branch.treeNodeToId,
        treeNodeFromId: this.branch.treeNodeFromId,
        limitType: this.branch.limitType,
        limitValue: this.branch.limitValue,
      });
      this.$message({
        message: "添加树枝成功!",
        type: "success",
      });
      // this.branch = {};
    },
    //添加树枝
    addBranch() {
      this.dialogVisible3 = true;
    },
    noLeafNodeHandleChange() {
      this.noLeafNode.level = this.num;
    },
    leafNodeHandleChange() {
      this.leafNode.level = this.num;
    },
    //添加叶子结点
    addLeafNode() {
      this.dialogVisible2 = true;
    },
    //添加非叶子结点
    addNoLeafNode() {
      this.dialogVisible = true;
    },
    // 添加叶子结点
    submitLeafNode() {
      this.leafNode.level = this.num;
      this.leafNode.treeNodeId = this.icreasingId++;
      console.log(this.leafNode);
      this.treeNodes.push({
        treeNodeType: 1,
        treeId: 0,
        treeNodeId: this.leafNode.treeNodeId,
        description: this.leafNode.description,
        level: this.leafNode.level,
      });
      // this.leafNode.description = null;
      this.$message({
        message: "添加叶子节点成功!",
        type: "success",
      });
      console.log(this.treeNodes);
    },

    
    //添加非叶子结点
    submitNoLeafNode() {
      this.noLeafNode.level = this.num;
      this.noLeafNode.treeNodeId = this.icreasingId++;
      console.log(this.noLeafNode);
      this.treeNodes.push({
        treeNodeType: 0,
        treeId: 0,
        treeNodeId: this.noLeafNode.treeNodeId,
        description: this.noLeafNode.description,
        ruleKey: this.noLeafNode.ruleKey,
        level: this.noLeafNode.level,
      });
      // this.noLeafNode.description = null;
      // this.noLeafNode.ruleKey = null;
      console.log(this.treeNodes);
      this.$message({
        message: "添加非叶子节点成功!",
        type: "success",
      });
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
.title {
  font-weight: bold;
  display: flex;
  align-content: center;
  justify-content: center;
  font-size: 30px;
  margin-top: 20px;
}
.buttons {
  display: flex;
  align-content: center;
  justify-content: space-between;
  margin-top: 20px;
}
.level {
  display: flex;
  justify-content: space-around;
  /* background-color: #398e3d; */
  margin-top: 10px;
  margin-left: 15px;
}
.treeNode {
  display: flex;
  justify-content: center;
  align-content: center;
}
.treeNode-left {
  background-color: #398e3d;
  color: aliceblue;
  font-weight: bold;
  border-radius: 50%;
  padding: 20px 20px;
}
.treeNode-right {
  display: flex;
  justify-content: center;
  align-content: center;
  margin-top: 20px;
  padding-left: 20px;
  font-weight: bold;
}
.holder {
  display: flex;
  justify-content: center;
  border-bottom: solid 1px #398e3d;
  padding-bottom: 10px;
}
.level1 {
  display: flex;
  justify-content: space-around;
  /* background-color: #398e3d; */
  margin-top: 10px;
  margin-left: 15px;
  font-weight: bold;
}
</style>