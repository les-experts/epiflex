. ./project.config

#jboss_home dans project.config
BACK_PATH=src/back
WEB_INF_PATH=WEB-INF
VUE_PATH=src/vue
STATIQUE_PATH=statique


CLASSPATH=./src:$JBOSS_HOME/modules/system/layers/base/javax/servlet/api/main/jboss-servlet-api_4.0_spec-1.0.0.Final.jar
#jboss-servlet-api_3.1_spec-1.0.0.Final.jar

javac -cp $CLASSPATH -sourcepath src -d $WEB_INF_PATH/classes $BACK_PATH/*.java

jar cf epiflex.war $WEB_INF_PATH $VUE_PATH $STATIQUE_PATH
cp epiflex.war $JBOSS_HOME/standalone/deployments

echo -e "\n\e]8;;http://localhost:8080/epiflex/src/vue/html_jsp/marketplace.jsp\aACCEDER AU SITE (CTRL+LCLICK)\e]8;;\a\n"
