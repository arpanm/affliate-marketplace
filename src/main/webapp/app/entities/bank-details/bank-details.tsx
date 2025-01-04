import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './bank-details.reducer';

export const BankDetails = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const bankDetailsList = useAppSelector(state => state.bankDetails.entities);
  const loading = useAppSelector(state => state.bankDetails.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="bank-details-heading" data-cy="BankDetailsHeading">
        <Translate contentKey="affliateMarketplaceApp.bankDetails.home.title">Bank Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.bankDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/bank-details/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.bankDetails.home.createLabel">Create new Bank Details</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bankDetailsList && bankDetailsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('accountName')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.accountName">Account Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountName')} />
                </th>
                <th className="hand" onClick={sort('accountNo')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.accountNo">Account No</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountNo')} />
                </th>
                <th className="hand" onClick={sort('bankName')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.bankName">Bank Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankName')} />
                </th>
                <th className="hand" onClick={sort('ifsc')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.ifsc">Ifsc</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ifsc')} />
                </th>
                <th className="hand" onClick={sort('proofUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.proofUrl">Proof Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('proofUrl')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.bankDetails.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bankDetailsList.map((bankDetails, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/bank-details/${bankDetails.id}`} color="link" size="sm">
                      {bankDetails.id}
                    </Button>
                  </td>
                  <td>{bankDetails.accountName}</td>
                  <td>{bankDetails.accountNo}</td>
                  <td>{bankDetails.bankName}</td>
                  <td>{bankDetails.ifsc}</td>
                  <td>{bankDetails.proofUrl}</td>
                  <td>{bankDetails.isActive ? 'true' : 'false'}</td>
                  <td>{bankDetails.createdBy}</td>
                  <td>
                    {bankDetails.createdOn ? <TextFormat type="date" value={bankDetails.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{bankDetails.updatedBy}</td>
                  <td>
                    {bankDetails.updatedOn ? <TextFormat type="date" value={bankDetails.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/bank-details/${bankDetails.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/bank-details/${bankDetails.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/bank-details/${bankDetails.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.home.notFound">No Bank Details found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default BankDetails;
