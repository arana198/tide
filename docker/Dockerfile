FROM java:8-alpine
MAINTAINER  Ankit Rana
EXPOSE  8001

RUN mkdir -p /opt/webapp/config
WORKDIR /opt/webapp

ADD entrypoint.sh /
RUN chmod a+x /entrypoint.sh

ADD tide-test.jar /opt/webapp/

ENTRYPOINT ["/bin/sh","/entrypoint.sh"]
