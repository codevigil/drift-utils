# drift-utils
Java Utilities Repository

### drift-core
    - Equalsbuilder based on runtime byte code manipulation
    - Deep copy and shallow copy
    - Parallel Counting Map
    - Next Permutation Generator
    
### drift-time
    Utilities around adding timestamps and versioning your data
    
### drift-instrumentation
    Runtime average object size calculation based on annotations
    Annotate your methods to get time logs

###drift-lock
    Provide a locking mechanism which is maintained at JVM level where granuality of lock is more than class or object.
    This locking mechanism will be share acros different threads and implementation can be of reenterantlock(better thab synchronized lock). Or implementation can be of different type providing a way to the users to speify what kind of locking mechanism they want.
    Need to analyse all existing locking mechanis before implementing it. Will list down all existing locking mechanism here.
    
### extras
    Hosts the payload-configuration utility, space for ad-hoc projects.



