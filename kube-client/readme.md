# 通过https访问k8s的apiServer
```
curl https://172.17.162.235:6443/api/v1/nodes \
--cacert /etc/kubernetes/pki/ca.crt \
--cert /etc/kubernetes/pki/apiserver-kubelet-client.crt \
--key /etc/kubernetes/pki/apiserver-kubelet-client.key
```
# 将k8s master节点 /etc/kubernetes/pki下的证书文件拷贝到 resources/ssl目录下



```
curl https://172.17.162.235:6443/api/v1/nodes --cacert D:\software\cloud\k8s-ssl\ca.crt --cert D:\software\cloud\k8s-ssl\apiserver-kubelet-client.crt --key D:\software\cloud\k8s-ssl\apiserver-kubelet-client.key
```