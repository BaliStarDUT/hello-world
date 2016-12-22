from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
    return HttpResponse("<h2>Hello,world. You're at the polls index.</h2>")

def detail(request,question_id):
    return HttpResponse("<h2>You're looking at question %s.</h2>" % question_id)

def results(request):
    response = "You're looking at the results of question %s."
    return HttpResponse(response % question_id)

def vote(request,question_id):
    return HttpResponse("<h2>You're voting on question %s.</h2>" % question_id)


