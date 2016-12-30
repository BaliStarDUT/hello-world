from django.shortcuts import render
from django.http import HttpResponse
from django.http import Http404
from django.template import loader
from .models import Question

# Create your views here.
def index(request):
    latest_question_list = Question.objects.order_by('-pub_date')[:5]
    context={
        'latest_question_list':latest_question_list,
    }
    return render(request,'polls/index.html',context)

def detail(request,question_id):
    try:
        question = Question.objects.get(pk=question_id)
    except Question.DoesNotExist:
        raise Http404("Qestion does not exist")
    return render(request,'polls/detail.html',{'question':question})
#HttpResponse("<h2>You're looking at question %s.</h2>" % question_id)

def results(request,question_id):
    response = "You're looking at the results of question %s."
    return HttpResponse(response % question_id)

def vote(request,question_id):
    return HttpResponse("<h2>You're voting on question %s.</h2>" % question_id)


