from flask import Flask
import os
import subprocess
app = Flask(__name__)

@app.route('/')
def get_top10():
    master = "local[*]"
    spark_class = "RoamingApp"
    project_path = "./target/scala-2.10/roamingproject_2.10-1.0.jar"
    spark_submit = "spark-submit --master %s --class %s %s"
    result = subprocess.check_output(spark_submit % (master,spark_class,project_path),shell=True)
    #result = subprocess.check_output("hdfs dfs -ls",shell=True)


    return result

if __name__=="__main__":
    app.run()
