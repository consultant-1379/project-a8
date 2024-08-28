import requests
import json

teams = requests.get("http://localhost:8080/teams").json()
print(teams)
print("\n")

print("id: " + teams[0]["id"])
print("\n")

employees = requests.get("http://localhost:8080/employees").json()
print(employees)


print("\n")
#print(employees[0])


def getTeamMembersForIndex(index, mylist):
    return "http://localhost:8080/teams/{0}/team-members".format(mylist[index]["id"])


baseurl="http://localhost:8080/teams/"

listurls = []
for i in range(len(teams)):
    listurls.append(getTeamMembersForIndex(i, teams))

teamMembersPerTeam = []
for i in range(len(teams)):
    print("\nteam members for team[%d]: " % i)
    teamMembersPerTeam.append(requests.get(listurls[i]).json())
    print(teamMembersPerTeam[i])

print("\n")


