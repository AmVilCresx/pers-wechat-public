FROM openjdk:8
MAINTAINER amvilcresx
LABEL name="pers-wechat-core" version="1.0.0.RELEASE" author="amvilcresx"
ADD ./target/wechat-core.jar pers-wechat-core.jar

ENV SERVER_PORT=8080
ENV NACOS_CFG_SERVER_ADDR=localhost:8848
ENV NACOS_CFG_DISCOVERY_SERVER_ADDR=${NACOS_CFG_SERVER_ADDR}
ENV NACOS_CFG_FILE_EXT_NAME=yaml
ENV NACOS_CFG_NAMESPACE=
ENV SPRING_APPLICATION_NAME=wechat-core

RUN echo 'NACOS_CFG_SERVER_ADDR=' $NACOS_CFG_SERVER_ADDR
RUN echo 'NACOS_CFG_NAMESPACE=' $NACOS_CFG_NAMESPACE
RUN echo 'NACOS_CFG_DISCOVERY_SERVER_ADDR=' $NACOS_CFG_DISCOVERY_SERVER_ADDR

ENV TZ 'Asia/Shanghai'
ENTRYPOINT ["sh", "-c", "java -jar -Dfile.encoding=UTF-8 pers-wechat-core.jar --server.port=$SERVER_PORT --spring.application.name=$SPRING_APPLICATION_NAME --spring.cloud.nacos.config.serverAddr=$NACOS_CFG_SERVER_ADDR --spring.cloud.nacos.discovery.server-addr=$NACOS_CFG_DISCOVERY_SERVER_ADDR --spring.cloud.nacos.config.fileExtension=$NACOS_CFG_FILE_EXT_NAME --spring.cloud.nacos.config.namespace=$NACOS_CFG_NAMESPACE"]
