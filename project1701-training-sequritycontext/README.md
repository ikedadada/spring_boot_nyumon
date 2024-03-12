:imagesdir: images

== ハンズオン
=== 概要
Spring Security の Security Context へアクセスし、ログイン中のユーザの情報を取得します。

=== 手順
. TrainingAdminController クラスの中身を見てみましょう。update メソッドの引数で、ユーザ情報を取得しています。そこからユーザ ID を取得し、TrainingAdminService オブジェクトの update メソッドに渡しています。TrainingAdminService オブジェクトの update メソッドの中では、AuditLogService オブジェクトの registerLog メソッドを呼び出していて、監査ログにユーザ ID が含められるようにしています。

. TrainingApplication クラスを実行し、ブラウザで http://localhost:8080/admin/training/display-list にアクセスしましょう。ログイン画面が表示されるので、ログイン ID に「taro」パスワードに[taro123」と入力しログインします。研修一覧画面で適当な研修のリンクをクリックし、研修の更新を完了させると、監査ログが登録されます。 audit_log テーブルの中身を確認するため、H2 データベースにアクセスするコンソールを使用します。 http://localhost:8080/h2-console にアクセスしてください。以下のようなログイン画面が表示されます。

- image::h2-login.png[]
- 以下のログイン情報を入力してください。
- |===
  | 項目 | 値

| ドライバクラス | org.h2.Driver
| JDBC URL | jdbc:h2:mem:testdb
| ユーザ名 | sa
| パスワード | (空)
|===

- ログインすると、以下のような画面が表示されます。
- image::h2-console.png[]
- 左上の「AUDIT_LOG」をクリックすると、テキストエリアに SELECT 文が自動的に記述されるので、その後「実行」ボタンを押下します。レコードが表示されるはずです。

. ユーザ ID の取得を Controller で行うよりも、AuditLogServiceImpl の中で取得した方がスマートです。Controller や Service の引数でユーザ情報を受け取る必要がなくなります。そのようにするにはどうすればよいでしょう？
.. 【ヒント】AuditLogServiceImpl の中で以下のソースコードを記述するとユーザ ID を取得できます。

- ***
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  String userId = authentication.getName();
  ***
