res/

1. Copy all of the XML files out of your layout directory, and put them into a directory on the desktop or something for backup.
2. Delete the entire layout directory (Make sure you backed everything up from step 1!!!)
3. Right click the res directory and select new > directory.
4. Name this new directory "layouts". (This can be whatever you want, but it will not be a 'fragment' directory or 'activity' directory, that comes later).
5. Right click the new "layouts" directory and select new > directory. (This will be the name of the type of XML files you will have in it, for example, 'fragments' and 'activities').
6. Right click the 'fragment' or 'activities' directory (Note: this doesn't have to be 'fragment' or 'activities' that's just what i'm using as an example) and select new > directory once again and name this directory "layout". (Note: This MUST be named 'layout'!!! very important).
7. Put the XML files you want inside the new 'layout' directory from the backup you made on your desktop.
8. Repeat steps 5 - 7 for as many custom directories as you desire.
9. Once this is complete, go into your modules gradle.build file and create a sourceSets definition like this...(Make sure 'src/main/res/layouts' & 'src/main/res' are always the bottom two!!!! Like I am showing below).

sourceSets {
    main {
        res.srcDirs =
                [
                        'src/main/res/layouts/activities',
                        'src/main/res/layouts/fragments',
                        'src/main/res/layouts/content',
                        'src/main/res/layouts',
                        'src/main/res'
                ]
    }
}