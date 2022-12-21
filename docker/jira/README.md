# docker私有化部署jira
## 使用mysql
1、

## 破解使用
在atlassian-agent.jar 目录下，执行。
```shell
java -jar atlassian-agent.jar \
    -d -m noodzhan@163.com -n DEV -p jira \
    -o http://192.168.0.200:8080/ -s BP7C-8LMS-P95T-QDEG
```
返回
```
====================================================
=======     Atlassian Crack Agent v1.3.1     =======
=======           https://zhile.io           =======
=======          QQ Group: 30347511          =======
====================================================

Your license code(Don't copy this line!!!): 

AAAB8w0ODAoPeJyNU12PojAUfedXkOzjBGzrKGhCsi4wOzigOKi7rxWv0lksWIozzq9fEGbnQ2M26
Uube84959zbb79grToQqwSr2Bj2yJAYqh3NVYIIUbYCgCdZnoPQfRYDL2B+zGFCd2DZ0yBwH21v5
Cu2ACpZxh0qwaqBGiYawcoViANFLFheo6wFT9mOyUpI2gDU1VFNpMyLYafzmrAUdJYpAWVcAqc8B
vclZ+LYdjMHGjKqozwxQd9UumvWUE98L/DmrqNMyt0KxHSzKEAUlvZP3BWuXGTrMpZ6fdGKbCOfq
QD9jOhKLY0lO4AlRQmfsvz4fgVeqaI2VK5FU9rGs6wa1+aIEpWr9xhPJe6BpuVpGNaGpkVL/5VoK
raUs6Kpq5OugsYDouO+qSOdIDQ0kYk6ip1xWWl1q+xTi2fZ+jWh/Dvud/U42zXUZ3G0Iu9pkViBj
ey7+/E4Xfy+DcnYmLnc6D73Zttt+RCHe3OULEYsAPJk3jzs0eLwyCZB5663onG4Xe49q2nxn2lFk
oraYeO7Ha/nWL7nRO5E83HfwP1K+62Bce/Ttlxa0AjEAUQF/xEatmb6QaSFg95cmznuT+UPHN+Gg
PsIGcjsdvGl33K+h2Ep4oQW8PWvfASfJpULVrSmK/nWBQvtdE7KHXf5F2EuRxswLAIUfCPKaP1Oh
HSknb27nHKDbo8UHQwCFFTZAw2/q8qr35F7sEj/+S8CQQsqX02nj
```

## Reusing a mysql database user on a new database caused Jira setup to fail with Database is not empty message
https://jira.atlassian.com/browse/JRASERVER-72389