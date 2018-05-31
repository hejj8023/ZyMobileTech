# 将aar发布到github并在项目中引用<参见项目MukeApps>
## 测试成功，仓库的类型必须是组织类型，非组织类型使用的时候直接发现不了
    https://www.jianshu.com/p/ca53952f4212

## 使用方式:

    project->build.gradle:
     allprojects {
         repositories {
             maven { url "https://raw.githubusercontent.com/zydeveloperteam/zysdk/master" }
         }
     }
    app->build.gradle:
    dependencies {
        /*引用的是https://github.com/zydeveloperteam/zysdk*/
        compile 'com.szzynt:common_lib:1.0.0'
    }

#  Android 快速发布开源项目到jcenter
https://blog.csdn.net/lmj623565791/article/details/51148825

# git push文件
    git config user.name "xxx"
    git config user.email "xxx@xxx.com"
    git add -A
    git add . (当前目录中的所有的文件)
    git commit -m "xxx"
    git push

github api验证
https://api.github.com/repos/syusikoku/maven_repo_sdk/releases/latest
# 要想在github上的仓库中看到release,即可以创建release发布需要下面的操作
    git tag v1.0.0
    git push origin v1.0.0

# Android studio下将项目代码上传至github包括更新，同步，创建依赖
https://blog.csdn.net/lyj1005353553/article/details/55518608

# 上传自己的库到github并作为依赖(***********重点***********>未测试成功 )
    https://blog.csdn.net/wr751811032/article/details/53485283
    
    流程:
     1. 在github上创建repo,然后将repo使用git clone到本地
     2. 将本地lib发布到repo,使用maven(在项目中使用gradle操作),然后使用git commit+push命令，git tag打tag
     3. 将库发布到JitPack,网址:https://jitpack.io
     4.使用https://github.com/syusikoku/maven_repo_sdk在界面中lookup
     5. 会找到库对应的版本，然后选择一个get即可
     6.使用如下:
       project: build.gradle: 
       allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }
       app: build.gradle:
         dependencies {
                compile 'com.github.syusikoku:maven_repo_sdk:v1.0.0'
         }
   	