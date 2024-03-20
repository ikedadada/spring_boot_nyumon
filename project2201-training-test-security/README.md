== ハンズオン
=== 概要
Spring Security のテストサポート機能を使って、ログインしているユーザの権限を指定しながらテストします。

=== 手順
. SecurityConfig クラスの中身を見てみましょう。Spring Security を有効にするコンフィグレーションが行われています。中の記述が何をしているか分かりますか？

. TrainingAdminControllerSecurityTest クラスを実行しましょう。Spring Security の認証済みかどうかのチェックにひっかかって、すべてテストが失敗します(ログイン画面にリダイレクトするための 302 ステータスコードが返却されます)。

. MockMvc と連携した Spring Security のテストサポート機能を使って、すべてのテストがパスするように修正しましょう。

. TrainingAdminServiceSecurityTest クラスを実行しましょう。認証したユーザ、もしくは、認証していないという情報が存在しないため、テストが失敗します。すべてのテストがパスするように修正しましょう。
