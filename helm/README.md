#运行程序
java -jar helm\target\helm-1.0-SNAPSHOT.jar -DBASE_NAMESPACE=212.64.90.199 -DMYSQL_USER=root -DMYSQL_PASSWORD=123456

http://localhost:8080/user/list

#docker build
将dockerfile上传至服务器/root/docker/app目录下
#编译过程中出现如下问题的解决方案
ERROR: http://dl-cdn.alpinelinux.org/alpine/v3.10/main: temporary error (try again later)
WARNING: Ignoring APKINDEX.00740ba1.tar.gz: No such file or directory
ERROR: http://dl-cdn.alpinelinux.org/alpine/v3.10/community: temporary error (try again later)
WARNING: Ignoring APKINDEX.d8b2a6f4.tar.gz: No such file or directory

vi /etc/default/docker
DOCKER_OPTS="--dns 114.114.114.114"
systemctl restart docker

docker build -t riil/user-app:1.0 /root/docker/app

#运行容器
docker run --name user_app -d -e BASE_NAMESPACE=212.64.90.199 \
 -e MYSQL_USER=root \
 -e MYSQL_PASSWORD=123456 \
 -p 8080:8080 \
 riil/user-app:1.0
 
http://172.17.162.235:8080/user/list

#导出镜像
docker save -o user-app.tar riil/user-app

#导入镜像
docker load -i user-app.tar

#同步镜像到236
scp /root/helm/user-app.tar root@172.17.162.236:/root/docker/user-app.tar
#在node节点上导入镜像
docker load -i user-app.tar

#helm
mkdir /root/helm/userapp
#将helm目录消的内容拷贝到/root/helm/userapp下
#check语法
helm lint userapp

helm install userapp userapp -set MYSQL_USER=SS

helm upgrade userapp userapp

helm uninstall userapp

#mysql
docker pull mysql

docker tag mysql riil/mysql:latest

docker save -o mysql.tar mysql

docker load -i user-app.tar

#查看chart目录下的依赖
```
[root@master-1 ~]# helm dependency list installation/helm/relax
NAME    VERSION REPOSITORY              STATUS         
kong    2.1.0.0 file://../kong/         invalid version
asset   2.1.0.0 file://../asset/        invalid version
```
