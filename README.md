## 个人公众号基础开发

### 1. pers-wechat-admin

```tex
素材管理、菜单管理
```

已制作一个简单的docker 镜像，拉取方式：`docker pull amvilcresx/pers-wehcat-admin:1.0.0` 使用说明：

* docker 启动命令

```dockerfile
docker run -e NACOS_CFG_SERVER_ADDR=192.168.195.1:8848 -e NACOS_CFG_NAMESPACE=cc1a88d8-5355-4850-a109-b1afda720957 -e NACOS_CFG_DISCOVERY_SERVER_ADDR=192.168.195.1:8848  -p 8080:8093 --name pers-wechat-admin  pers-wechat-admin
```

​	配置中心用的是Nacos，参数说明：

| 参数                            | 说明                               |
| ------------------------------- | ---------------------------------- |
| NACOS_CFG_SERVER_ADDR           | Nacos配置中心地址                  |
| NACOS_CFG_NAMESPACE             | Nacos 命名空间                     |
| NACOS_CFG_DISCOVERY_SERVER_ADDR | Nacos 服务发现地址                 |
| NACOS_CFG_FILE_EXT_NAME         | Nacos配置文件后缀名                |
| SPRING_APPLICATION_NAME         | 应用名称${spring.application.name} |

> 注：Nacos 的dataId识别规则： `${prefix}-${spring.profiles.active}.${file-extension}`
>
> ​	1.${prefix} : 可通过 `--spring.cloud.nacos.config.prefix=xxx` 配置，若不配置，则默认是 `${spring.application.name}`。
>
>  2. ${spring.profiles.active}: 当前环境的profile, 如 `dev`。
>
>  3. ${file-extension}：配置文件的后缀名, 默认是`properties`
>
>     ![](https://coolcollege-storage-hz.oss-cn-hangzhou.aliyuncs.com/image-20210601155340916.png)
>
> 例如：
>
>    ```yaml
> spring:
>   cloud:
>     nacos:
>       config:
>         file-extension: yaml
>         server-addr: 192.168.195.1:8848
>         prefix: cfg-prefix
>         encode: UTF-8
>         namespace: cc1a88d8-5355-4850-a109-b1afda720957
>       discovery:
>         server-addr: 192.168.195.1:8848
>    profiles:
>     active: local
>   application:
>     name: wechat-admin
>    ```
>
> 对应Nacos的dataId： `cfg-prefix-local.yaml`.

### 2. pers-wechat-core

```tex
响应用户消息
```

已制作一个简单的docker 镜像，拉取方式：`docker pull amvilcresx/pers-wehcat-core:1.0.0`, 启动及配置 `pers-wechat-admin`.