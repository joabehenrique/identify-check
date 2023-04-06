# 🙅‍♂️ Identify Check

![GitHub repo size](https://img.shields.io/github/repo-size/joabehenrique/identify-check?style=flat)
![GitHub language count](https://img.shields.io/github/languages/count/joabehenrique/identify-check?style=flat)
![GitHub forks](https://img.shields.io/github/forks/joabehenrique/identify-check?style=flat)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/joabehenrique/identify-check?style=flat)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/joabehenrique/identify-check?style=flat)

> Project developed with the intention of optimizing the control of CPFs with risk of fraud.

## 💻 Requirements

Before you begin, make sure you have met the following requirements:

- You need the version `17` of `Java/JDK`, `PostgreSQL` on your machine.

If you need installation instructions, [click here](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiJ7Ieqxs39AhUwppUCHWvFBVoQFnoECAkQAQ&url=https%3A%2F%2Fjava.tutorials24x7.com%2Fblog%2Fhow-to-install-java-17-on-windows&usg=AOvVaw0NRBuZwgs0vrM5_YVqmD20).

## 🚀 Installing Identify Check


To install Identify Check, follow these steps:

1º Start your Postgres database server and configure 

2º Run the project on your intellij just build and run

## ☕ Using Identify Check

To use Identify Check, follow these steps:

```
The project is a challenge for a MaxMilhas practical test, it 
consists of a control of CPFs in a spreadsheet. In this spreadsheet 
are added CPFs with risk of fraud. With the increase in our customer 
base, it has become increasingly difficult to maintain manual control.

With this in mind, the team's Product Owner raised the main functional 
requirements for the development of a system to control the CPFs by adding 
them to a restricted list.These requirements were implemented by me in the 
solution for this project. 
```
Getting a cpf
```
(GET) api/v1/cpf/{cpf}
```
Getting all cpf 
```
(GET) api/v1/cpf
```
Deleting a cpf
```
(DELETE) api/v1/cpf/{cpf}
```
Creating a cpf
```
(POST) api/v1/cpf

{
  "cpf": "63793028089"
}
```
Changing a cpf
```
(PUT) api/v1/cpf/{cpf}

{
  "cpf": "01804525090"
}
```

## 📫 Contributing to Identify Check

To contribute to Identify Check, follow these steps:

1. Fork this repository.
2. Create a branch: `git checkout -b <nome_branch>`.
3. Make your changes and commit them: `git commit -m '<commit_message>'`
4. Send to the original branch: `git push origin API-Movie / <local>`
5. Create the pull request.

Alternatively, see the GitHub documentation at [how to create a pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request)..

## 🤝 Author and Contributors

We thank the following people who contributed to this project:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/joabehenrique">
        <img src="https://avatars3.githubusercontent.com/u/64988299" width="100px" style="border-radius: 90px" alt="Foto do Joabe Henrique no GitHub"/><br>
        <sub>
          <b>Joabe Henrique [Author]</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

## 😄 Be one of the contributors<br>

Do you want to be part of this project? Click [here](https://github.com/joabehenrique/identify-check/blob/master/CONTRIBUTING.md) and read how to contribute.

## 📝 License

This project is under license. See the [license](https://github.com/joabehenrique/identify-check/blob/master/LICENSE.md) file for more details.
