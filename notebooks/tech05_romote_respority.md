#两种方式的实现
## 第一种是以aar的形式提交到github
### 1. 将android lib添加maven，将生成的aar上传到github的组织仓库中
前提，先在github创建组织类型的仓库，然后使用git clone将仓库克隆到本地

    apply plugin: 'maven'
    ext {
        // 从Github上clone下来的项目的本地地址
        // GITHUB_REPO_PATH = "F:\\workspace\\as\\zzg\\zysdk"
        GITHUB_REPO_PATH = "F:\\workspace\\as\\zzg\\maven_repo_sdk\\repository"
        // compile 'com.audienl:super_library:1.0.0'
        PUBLISH_GROUP_ID = 'com.szzynt'
        PUBLISH_ARTIFACT_ID = 'common_lib'
        PUBLISH_VERSION = '1.0.0'
    }
    uploadArchives {
        repositories.mavenDeployer {
            def deployPath = file(project.GITHUB_REPO_PATH)
            repository(url: "file://${deployPath.absolutePath}")
            pom.project {
                groupId project.PUBLISH_GROUP_ID
                artifactId project.PUBLISH_ARTIFACT_ID
                version project.PUBLISH_VERSION
            }
        }
    }
    
    // 源代码一起打包(不需要打包源代码的不要添加这几行)
    task androidSourcesJar(type: Jar) {
        classifier = 'sources'
        from android.sourceSets.main.java.sourceFiles
    }
    
    artifacts {
        archives androidSourcesJar
    }
    
###  在项目目录 build   uploadArchives执行做任务生成aar
###  使用git将aar提交到远程仓库
   git add -A / git add .
   git commit -m "aaa"
   git push

### 3.使用,在需要使用的藏在的project build.gradle,通过
    maven { url "https://raw.githubusercontent.com/zydeveloperteam/zysdk/master" }
   zydeveloperteam/zysdk为仓库地址
### 4. 项目使用(把代理关闭掉):
   compile 'com.szzynt:common_lib:1.0.0' 
   分别对应如下
   PUBLISH_GROUP_ID = 'com.szzynt'
   PUBLISH_ARTIFACT_ID = 'common_lib'
   PUBLISH_VERSION = '1.0.0'
   
## 第二种,直接将lib分布
### 1. 先将项目分享到github,清除掉app工程,上传到github
### 2. 在github找到该项目https://github.com/syusikoku/ZYSdk，找到release创建release发布
### 3. create new release，创建发布版本
### 4. 发布到jitpack.io
    https://github.com/syusikoku/ZYSdk/v1.0.0 复制到jitpack.io->lookup
### 5. 点击版本1.0.0 -> get it
### 6. 使用(不要使用代理)
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    
    dependencies {
        compile 'com.github.syusikoku:ZYSdk:v1.0.0'
    }
    