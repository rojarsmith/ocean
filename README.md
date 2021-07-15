# Ocean

The open source distributed cloud service that provide professional RBAC management.

This system can help for building the new cloud service rapidly.

### Debug

Program arguments:

```bash
# For windows
--spring.config.additional-location="C:\\my\\build\\service\\ocean\\config\\common\\" --spring.profiles.active=dev
```



### Key

```bash
keytool -genkey -alias oauth2-dev -keyalg RSA -keystore oauth2-dev.jks -keysize 2048

password 12341234

keytool -importkeystore -srckeystore oauth2-dev.jks -destkeystore oauth2-dev.jks -deststoretype pkcs12

keytool -list -rfc --keystore oauth2-dev.jks | openssl x509 -inform pem -pubkey
```







## Developer Talk

### [2021/7/8]

The nice begin.

