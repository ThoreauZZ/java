#!/bin/sh

OLD_EMAIL=$1

git filter-branch --env-filter '
an="$GIT_AUTHOR_NAME"
am="$GIT_AUTHOR_EMAIL"
cn="$GIT_COMMITTER_NAME"
cm="$GIT_COMMITTER_EMAIL"

echo $GIT_COMMITTER_EMAIL
if [ "$GIT_COMMITTER_EMAIL" = "${OLD_EMAIL}" ]
then
    cn="thoreau"
    cm="zz.thoreau@gmail.com"
fi
if [ "$GIT_AUTHOR_EMAIL" = "${OLD_EMAIL}" ]
then
    an="thoeau"
    am="zz.thoreau@gmail.com"
fi
export GIT_AUTHOR_EMAIL="$am"
export GIT_COMMITTER_NAME="$cn"
export GIT_COMMITTER_EMAIL="$cm"
'
