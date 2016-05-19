if [ ! -e $HOME/.m2/repository.zip ]; then
  wget https://github.com/hsunsmile/clj-sql-builder/files/272844/repository.zip -P $HOME/.m2
  unzip $HOME/.m2/repository.zip -d $HOME/.m2
fi

if [ ! -e $HOME/.bin/boot ]; then
  curl -fsSLo $HOME/.bin/boot https://github.com/boot-clj/boot-bin/releases/download/latest/boot.sh
  chmod 755 $HOME/.bin/boot
fi

$HOME/.bin/boot deps
