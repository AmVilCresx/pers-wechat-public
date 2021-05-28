FROM openjdk:8
MAINTAINER amvilcresx
LABEL name="pers-wechat-admin" version="1.0.0.RELEASE" author="amvilcresx"
ADD ./target/wechat-admin.jar pers-wechat-admin.jar

ENV SERVER_PORT=8081
ENV NACOS_CFG_SERVER_ADDR=192.168.195.1:8848
ENV NACOS_CFG_FILE_EXT_NAME=yaml
ENV NACOS_CFG_NAMESPACE=
ENV SPRING_APPLICATION_NAME=pers-wechat-admin

RUN echo 'NACOS_CFG_SERVER_ADDR=' $NACOS_CFG_SERVER_ADDR
RUN echo 'NACOS_CFG_NAMESPACE=' $NACOS_CFG_NAMESPACE

ENV TZ 'Asia/Shanghai'
# ENTRYPOINT ["sh", "-c", "java -jar -Dfile.encoding=UTF-8 pers-wechat-admin.jar --server.port=$SERVER_PORT --spring.application.name=$SPRING_APPLICATION_NAME --spring.cloud.nacos.config.serverAddr=$NACOS_CFG_SERVER_ADDR --spring.cloud.nacos.config.fileExtension=$NACOS_CFG_FILE_EXT_NAME --spring.cloud.nacos.config.namespace=$NACOS_CFG_NAMESPACE"]
ENTRYPOINT ["sh", "-c", "java -jar -Dfile.encoding=UTF-8 pers-wechat-admin.jar --server.port=8099 --spring.application.name=wechat-admin --spring.cloud.nacos.config.serverAddr=192.168.195.1:8848 --spring.cloud.nacos.config.fileExtension=yaml --spring.cloud.nacos.config.namespace=cc1a88d8-5355-4850-a109-b1afda720957"]

